# Frontend Branch

NAMENSKONVENTIONEN
- variablen: <typ des UI><Bezeichnungen> zb. textFieldStadt
- methode: englische sinnvolle Bezeichnung z.b addPersonal()
- .fxml: mit unterstrich z.b Personal_PopUp.fxml
- Pop-up wird so geschrieben --> PopUp und nicht --> Popup



Aufgabenbereiche:

HÖCHSTE PRIORITÄT

- Räume erzeugen [Daniel]
    PopUp "Name" zum eingeben hinzufügen

- Reservierungen erzeugen [Dominik]
- Personal erzeugen [Dennis]
- Benutzerhandbuch als PDF für Olbertz [Dominik]
- Kontextmenüs der Tabellen (löschen, bearbeiten usw.) [Jonas]

- Spalte "Personalnr" bei Personal entfernen [Dennis]



SONSTIGES (wenn Zeit ist)

- logo verbessern für anmeldung
- desktop icon design
- fehlerdialog für falsche eingaben implementieren(erst Später)
- Hilfsmethode um Fehlerdialog mit beliebiger Fehlermeldung zu öffnen [Daniel]














ERLEDIGT-PROTOKOLL
[erledigt] CSS-Styling der GUI (einheitliche stylesheets) [Jonas]
[erledigt] Popups sollten Anwendung im Hintergrund "deaktivieren" (man sollte vor allem nicht mehr zu den anderen Ansichten wechseln können) [Moritz, Dominik, Dennis]
[erledigt] fenster soll nicht rumbuggen [Martin]
[erledigt] Titel richtig setzen( Im HOME soll oben HOME stehen statt Knuth) [Martin]
[erledigt] fenstergröße auf 1400 x 800 einstellen [Dominik]
[erledigt] login hardcoden [Moritz]
[erledigt] eingabefelder statisch/unbeweglich machen [Martin]
[erledigt] Bezeichnungen für die Tabellen hinzufügen (oben oder unten) [Dominik]
[erledigt] Raumnr spalte fehlt [Dominik]
[erledigt] Textfelder etwas größer machen  
[erledigt] Geburtstag als Datepicker implementieren [Dennis]
[erledigt] Datum Eingabe (Beginn/Ende) mit Datepicker ersätzen  [Dennis]
[erledigt] Spaltenbeschrieftung bei indikator entfernen [Jonas]
[erledigt] wird noch angepasst anhand skizze im dc  [Martin, Moritz]
[erledigt] Textfelder größer machen  [Dennis]
[erledigt] Abstand erhöhen           [Dennis]
[erledigt] Geburtstag als Datepicker implementieren [Dennis]
[erledigt] Bestätigen mit Enter zusätzlich zu Button [Moritz]
[erledigt] raumtyp als choicebox implementieren [Dennis]
[erledigt] Choicebox entfernen (durch Combobox ersetzen)
[erledigt] spalte für zustand hinzufügen (beschriftung leer pls) [Dennis]
[erledigt] Exceptions tunneln [Moritz]
[erledigt] herausfinden, warum sich die getunnelten exceptions nicht fangen lassen [Moritz]
[erledigt] Exceptions die durch Backend/Http-Request geworfen werden handeln [Moritz]
[erledigt] buttons (und andere elemente), die aktuell fokus haben sollten hervorgehoben werden (aktuell meist nicht sichtbar, außer für Text fields)[Jonas]
[erledigt - nicht nötig] Null handling (z.B. "zip": null) (nicht mit "zip": "null" verwechseln)
[erledigt] Preisbestimmung festlegen (fester Preis für jeden Raumtyp oder auch abhängig von Bettanzahl?)
[erledigt] anzeigen für "freie" und "belegte" Räume (vorerst labels oder eine andere alternative) implementieren [Martin]
[erledigt] Raum, Name, Vorname von Buchungen, die heute beginnen (/readBB (beginning bookings))
[erledigt] Raum, Name, Vorname von Buchungen, die heute frei werden (/readEB (Ending Bookings))
[erledigt] alle Reservierungen fetchen -> feth is done
[erledigt] Exisitierende Raumtypen abfragen (room/readT) -> feth is done
[erledigt] alle Mitarbeiter fetchen -> done
[erledigt] Mitarbeiter erzeugen      -> done    <endpoint /create was updated>
[erledigt] Mitarbeiter bearbeiten (update by Id) -> done
[erledigt] Mitarbeiter Adresse bearbeiten /updateA
[erledigt] update user by email or phone -> done
[erledigt] Raumstatus wird im Backend hinzugefügt (0,1,2)
[erledigt] Aufräumen (z.b. Datenbankverbindungen) nötig? - nicht nötig
[erledigt] Beenden der Datenbank? - nicht nötig