# Require4Testing

## Projektbeschreibung

Require4Testing ist eine Webanwendung zur Organisation manueller Anwendertests.  
Sie ermöglicht die Verwaltung von Anforderungen (Requirements), Testfällen (Test Cases), Testläufen (Test Runs) sowie Testern und Benutzern.

## Features

- Anlegen, Ändern, Löschen und Anzeigen von Anforderungen  
- Verwaltung von Testfällen und deren Zuordnung zu Anforderungen  
- Erstellung und Verwaltung von Testläufen  
- Verwaltung von Testern und Benutzern  
- REST-API mit Endpunkten für CRUD-Operationen  
- Persistenz der Daten mit Spring Data JPA und einer H2-Datenbank  
- Automatisierte Unit- und Integrationstests für Controller  

## (Stand: 02.07.2025)

- Grundgerüst mit Spring Boot aufgesetzt  
- Entitäten (Requirement, TestCase, TestRun, Tester, User) modelliert  
- Repository- und Service-Schichten implementiert  
- REST-Controller für Anforderungen (RequirementController) mit CRUD-Endpunkten erstellt  
- JUnit-Tests für RequirementController implementiert (Testklasse RequirementControllerTest)  
- Projekt erfolgreich gebaut und getestet  
- Projekt ins GitHub-Repository gepusht

## (Stand: 03.07.2025)

- REST-Controller für Testfälle (TestCaseController) mit        CRUD-Endpunkten implementiert  
- JUnit-Tests für TestCaseController erstellt  
- Anwendung erfolgreich gestartet und REST-Endpunkte für Requirements und Testcases getestet  
- H2 In-Memory-Datenbank korrekt konfiguriert und im Einsatz  
- GitHub Repository aktualisiert und Änderungen gepusht

## (Stand 04. – 06.07.2025)
	- REST-Controller, Service und Repository für TestRun, Tester und User implementiert
	- Entsprechende Unit-Tests für RequirementController, TesterController und UserController hinzugefügt
	- Datenmodell für Tester und User vervollständigt
	- CRUD-Operationen für TestRun, Tester und User erfolgreich getestet
	- Projektstruktur erweitert und konsolidiert
	- README mit aktuellem Projektfortschritt ergänzt

## Stand 07. - 09.07.2025
	- JSR-303 Bean Validation in den Datenmodellen ergänzt (Pflichtfelder, Formatvalidierung)
	- REST-Controller mit @Valid Annotation zur Aktivierung der Validierung versehen
	- Globale Fehlerbehandlung via @ControllerAdvice implementiert, um Validierungsfehler als JSON zu senden
	- Einheitliche und saubere Rückgabe von Validierungsfehlern an Clients sichergestellt
	- Tests überprüft und Validierungsfälle getestet
	- Backend nun robust gegenüber fehlerhaften Eingaben


## Stand 10.07.2025
	- Frontend mit Thymeleaf für Requirement, Tester, TestRun und User vollständig implementiert
	- Controller für die Frontend-Seiten (Thymeleaf) für alle Kern-Entitäten erstellt
	- Indexseite und IndexController für Navigation implementiert
	- Erste REST-API-Controller bereits angelegt (separat, nicht final integriert)
	- Problem mit Pfad-Weiterleitungen (/testers vs. /testers/) erkannt und dokumentiert
	- Anwendung läuft stabil, aber Pfadproblematik beim Zugriff auf URLs ohne Slash bleibt offen

# Aktueller Stand (11.07 - 15.07.2025)
	- Alle Thymeleaf-Templates (Requirements, Tester, TestRun, User) final überarbeitet und optisch vereinheitlicht
	- Einheitliches Button-Design und konsistente Navigation mit „Zurück zur Startseite“-Buttons umgesetzt
	- Umfangreiche Validierungen und Fehlermeldungen im Frontend integriert (JSR-303 Bean Validation + Thymeleaf)
	- Detaillierte Frontend-Dokumentation vorbereitet und mit Screenshots untermauert
	- Vollständige Vorbereitung der Screenshots für die Dokumentation (Workflows: Anlegen, Bearbeiten, Übersichten)
	- Code finalisiert und in Repository gepusht
	- Anwendung läuft stabil, alle Kernfunktionen sind lauffähig und konsistent bedienbar

## Nächste Schritte 
- Fokus auf Datenbankmodell und UML-Diagramm zur Ergänzung der Fallstudie 
- Fertigstellung der finalen Dokumentation (inkl. Deployment-Abschnitt, Screenshots und Diagramme)  
- Vorbereitung der endgültigen Abgabeversion der Fallstudie

---
