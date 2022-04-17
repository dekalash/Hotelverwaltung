package frontend.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

import frontend.layout.Launcher;
import javafx.application.Platform;

/**
 * Class guarding the application's execution by logging exceptions.
 * This class ensures that all exeptions are either caught at a lower level or logged
 * (lower level means e.g. inside a controller class).
 * <br><br>
 * <b>Code that is not (indirectly) invoked by this class's runApplicationBoxed() method may not throw any exceptions.</b>
 * <br><br>
 * This should not be a concern as the only methods executed before runApplicationBoxed() are Launcher.main()
 * and Launcher.start() (which invokes runApplicationBoxed() to start the application).
 */
public class Box {

    private static final String LOG_FILE_PATH = "target/log/log.txt";
    private static final String EXCEPTION_PRINT_STREAM = "error in print stream";
    private static final String FILE_NOT_FOUND = "file not found or could not be opened";
    private static final String LOG_NOTIFICATION = "exception was logged to "; //trailing whitespace

    /*
     * ansi escape codes for foreground color
     */
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    /**
     * ansi escape code to reset foreground color
     */
    private static final String RESET = "\u001B[39m";
    /**
     * Used to separate stack traces of different log entries for easier readability.
     * Also used to highlight logging failure in console output.
     */
    private static final String OUTPUT_SEPARATOR = "---------------------------------------------------------------------------";

    private static final String UNCAUGHT_HANDLER = "uncaught exception handler";
    private static final String LOG_FAILURE_MSG = "logging might have failed: "; //trailing whitespace
    private static final String FILE_CREATION = "an IO error occured while creating the log file";

    /**
     * Aborts the application.
     * Cleans the application's environment (e.g. closes connection to backend)
     * and invokes Platform.exit to shut down the application.
     * <br><br>
     * Should always be used when shutting down the application.
     */
    public static void abortApplication() {
        /* be aware that this might be called before the main application is even running 
        (e.g. login screen is closed)
        depending on the future implementation it may be necessary to only
        call this when the main application is running */
        cleanUp();
        Platform.exit();
    }

    /**
     * Cleans the application's environment (e.g. closes connection to backend).
     */
    public static void cleanUp() {
        //turned out to not be necessary
    }

    /**
     * Sets up the uncaught exception handler to log any exceptions passed into it.
     */
    private static void setUpExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread terminatedThread, Throwable throwable) {
                logByUncaughtExceptionHandler(throwable, terminatedThread);
                //logAndPrintStackTrace(throwable, UNCAUGHT_HANDLER, terminatedThread);
                abortApplication();
            }
        });
    }

    /**
     * Executes the application and takes care of uncaught exceptions.
     */
    public static void runApplicationBoxed() {
        /*  try catch is necessary as javafx catches exceptions that bubble up 
            to Application.start() / Launcher.start(). 
            Wihtout try-catch here only exceptions that kill their thread would be handled by the
            UncaughtExceptionHandler and logged. */
        try {
            setUpExceptionHandler();
            Launcher.displayLogin();
        } catch (Exception exception) {
            log(exception, "runApplicationBoxed");
            Box.abortApplication();
        }
    }

    /**
     * Prints a logging notifcation to standard output.
     */
    private static void printLoggingNotification() {
        System.out.println("\n" + YELLOW + LOG_NOTIFICATION + LOG_FILE_PATH + RESET);
    }

    /**
     * Prints a warning about logging failure to the standard output.
     * <br><br>
     * Format: "Logging might have failed: {cause}"
     * @param cause description why the logging is deemed to have failed
     */
    private static void printWarning(String cause) {
        System.out.println(OUTPUT_SEPARATOR);
        System.out.println("\t" + RED + LOG_FAILURE_MSG + cause + RESET);
        System.out.println(OUTPUT_SEPARATOR);
    }

    /**
     * Creates the log file and it's parent directories if it does not yet exist.
     * @return the log file
     * @throws IOException if an IOException occurs when creating the file
     */
    private static File createLogFile() throws IOException {
        File logFile = new File(LOG_FILE_PATH);
        logFile.getParentFile().mkdirs();
        logFile.createNewFile();
        return logFile;
    }

    /**
     * Prints data to the log file as defined by {@link Box#log(Throwable, String, Thread)}.
     * @param logFile the file to write to
     * @param throwable the exception to be logged
     * @param invokedBy the name of the method that invoked Box.log()
     *  or "uncaught exception handler" if it was invoked by the uncaught exception handler
     * @param terminatedThread the thread on which the throwable (e.g. exception) was thrown 
     */
    private static void printToLogFile(File logFile, Throwable throwable, String invokedBy, Thread terminatedThread) {
        //new FileOutputStream(logFile, true) appends to the file rather than overwriting it
        try (PrintStream printStream = new PrintStream(new FileOutputStream(logFile, true))) {

            //begin new entry
            printStream.append(OUTPUT_SEPARATOR).append('\n');

            //include timestamp
            printStream.append(new Date().toString()).append(":\n");

            //print information about invocation
            printStream.append("logging was invoked by ").append(invokedBy).append('\n');
            printStream.append("thread: ").append(terminatedThread.toString()).append('\n');

            //print stack trace
            throwable.printStackTrace(printStream);

            if (printStream.checkError()) {
                printWarning(EXCEPTION_PRINT_STREAM);
            }

        } catch (FileNotFoundException exception) {
            //thrown if the FileOutPutStream cannot open or create the file
            printWarning(FILE_NOT_FOUND);
        }
    }

    /**
     * Logs the throwable's stack trace.
     * Prints a logging notifcation to standard output.
     * <br><br>
     * Log entries include the current time stamp and where the logger was invoked (e.g. method name).
     * This information can be used to monitor which exceptions are handled by the uncaught exception handler.
     * <br><br>
     * The log file is located inside the the target folder.
     * The file serves debugging purposes only and may be deleted if it does not contain any unexpected errors.
     * Reviewing the file before deleting it is desirable.
     * The log file should not be pushed to the git repository.
     * Invoking "mvn clean" removes the entire target folder, including this log file.
     * @param throwable the throwable (e.g. exception) that was caught and should be logged
     * @param invokedBy the name of the method that invoked Box.log() 
     *  or "uncaught exception handler" if it was invoked by the uncaught exception handler
     * @param terminatedThread the thread on which the throwable (e.g. exception) was thrown 
     */
    public static void log(Throwable throwable, String invokedBy, Thread terminatedThread) {
        try {
            printLoggingNotification();
            File logFile = createLogFile();
            printToLogFile(logFile, throwable, invokedBy, terminatedThread);
        } catch (IOException exception) {
            //thrown by createLogFile()
            printWarning(exception.getMessage());
        }
    }

    /**
    * Logs the throwable as specified by {@link Box#log(Throwable, String, Thread)}.
    */
    public static void log(Throwable throwable, String invokedBy) {
        log(throwable, invokedBy, Thread.currentThread());
    }

    /**
     * Logs a throwable as specified by {@link Box#log(Throwable, String, Thread)} 
     * and marks the log entry as being invoked by the uncaught exception handler.
     */
    public static void logByUncaughtExceptionHandler(Throwable throwable, Thread terminatedThread) {
        log(throwable, UNCAUGHT_HANDLER, terminatedThread);
    }

    /**
     * Logs the throwable as specified by {@link Box#log(Throwable, String, Thread)}
     * and prints the throwable's stack trace to the standard output.
     */
    private static void logAndPrintStackTrace(Throwable throwable, String invokedBy, Thread terminatedThread) {
        throwable.printStackTrace(new PrintStream(System.out));
        log(throwable, invokedBy, terminatedThread);
    }

    /**
     * Logs the throwable as specified by {@link Box#log(Throwable, String, Thread)}
     * and prints the throwable's stack trace to the standard output.
     */
    public static void logAndPrintStackTrace(Throwable throwable, String invokedBy) {
        logAndPrintStackTrace(throwable, invokedBy, Thread.currentThread());
    }
}
