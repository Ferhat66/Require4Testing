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

## Aktueller Stand (04.–06.07.2025)
	- REST-Controller, Service und Repository für TestRun, Tester und User implementiert
	- Entsprechende Unit-Tests für RequirementController, TesterController und UserController hinzugefügt
	- Datenmodell für Tester und User vervollständigt
	- CRUD-Operationen für TestRun, Tester und User erfolgreich getestet
	- Projektstruktur erweitert und konsolidiert
	- README mit aktuellem Projektfortschritt ergänzt

## Nächste Schritte

- Frontend (z.B. Thymeleaf Templates oder React) implementieren  
- Weitere Tests schreiben und automatisieren, insbesondere für neue Controller  
- Dokumentation erweitern (Architektur, UML-Diagramme, Screenshots)  
- Erweiterte Features implementieren, z.B. Zuordnung von Testfällen zu Testläufen und Pflege von Testergebnissen

---
