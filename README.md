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

## Aktueller Stand (Stand: 02.07.2025)

- Grundgerüst mit Spring Boot aufgesetzt  
- Entitäten (Requirement, TestCase, TestRun, Tester, User) modelliert  
- Repository- und Service-Schichten implementiert  
- REST-Controller für Anforderungen (RequirementController) mit CRUD-Endpunkten erstellt  
- JUnit-Tests für RequirementController implementiert (Testklasse RequirementControllerTest)  
- Projekt erfolgreich gebaut und getestet  
- Projekt ins GitHub-Repository gepusht

## Nächste Schritte

- REST-Controller für weitere Entitäten (TestCase, TestRun, Tester, User) erstellen  
- Frontend (z.B. Thymeleaf Templates oder React) implementieren  
- Weitere Tests schreiben und automatisieren  
- Dokumentation erweitern (Architektur, UML-Diagramme, Screenshots)  
- Erweiterte Features wie Zuordnung von Testfällen zu Testläufen und Pflege von Testergebnissen implementieren

---
