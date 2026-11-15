# MSL Maintenance

> Bozza iniziale: il documento verrà completato man mano che il progetto avanza.

Backend REST per la gestione della manutenzione industriale: impianti, macchine, ordini di lavoro (ticket), interventi dei tecnici e magazzino ricambi. Progetto d'esame del corso Backend Programming.

## Idea

Un'azienda con uno o più stabilimenti deve tenere traccia di guasti, manutenzioni programmate e miglioramenti sulle proprie macchine. I manager aprono e assegnano gli ordini di lavoro, i tecnici registrano gli interventi e i ricambi usati, e il sistema produce statistiche utili (tempi di fermo, ricambi sotto scorta, macchine più problematiche).

## Stack

- Java 21, Spring Boot 4.1.2 (Spring Web MVC, Spring Data JPA, Spring Security)
- PostgreSQL
- Autenticazione JWT (JJWT)
- Cloudinary (immagini), Mailgun (email), Open-Meteo (meteo)

## Modello dati (sintesi)

- `User`, `Plant`, `Area`, `Asset`, `WorkOrder`, `Intervention`, `SparePart`, `PartUsage`, `Comment`, `Attachment`
- Ereditarietà JOINED:
  - `WorkOrder` → `CorrectiveOrder`, `PreventiveOrder`, `ImprovementOrder`
  - `Asset` → `PlcAsset`, `RobotAsset`, `DriveAsset`

## Ruoli

| Ruolo | Cosa può fare |
|---|---|
| `TECNICO` | vede i ticket assegnati a lui, registra interventi e ricambi, commenta |
| `MANAGER` | crea, assegna e chiude ticket, gestisce impianti, macchine e ricambi, vede i report |
| `ADMIN` | tutto, più gestione utenti e ruoli |

## Integrazioni di terze parti

- **Cloudinary**: immagine profilo utente e foto dei guasti.
- **Mailgun**: email di assegnazione e chiusura ticket.
- **Open-Meteo**: temperatura ambiente dello stabilimento salvata nei guasti, usata per le statistiche.

## Funzionalità previste

- Registrazione, login e aggiornamento del profilo (con immagine)
- CRUD di stabilimenti, aree, macchine, ordini di lavoro, interventi e ricambi
- Filtri, ordinamento e paginazione sui ticket
- Statistiche: ore per tecnico, tempo medio di riparazione per macchina, ricambi sotto scorta, macchine con più guasti
- Gestione errori uniforme e validazione degli input
- Extra (da valutare): sezione GraphQL per i report

## Avvio

1. Installare Java 21 e PostgreSQL, creare un database `maintenance`.
2. Copiare `env.properties.example` in `env.properties` e compilarlo.
3. Eseguire `./mvnw spring-boot:run`.

### Variabili d'ambiente

| Variabile | Descrizione |
|---|---|
| `DB_URL`, `DB_USER`, `DB_PASS` | connessione a PostgreSQL |
| `JWT_SECRET` | chiave di firma dei token (almeno 32 caratteri) |
| `CLOUDINARY_NAME`, `CLOUDINARY_KEY`, `CLOUDINARY_SECRET` | credenziali Cloudinary |
| `MAILGUN_KEY`, `MAILGUN_DOMAIN`, `MAILGUN_FROM` | credenziali Mailgun |

## Test delle API

La collection Postman verrà inserita nella cartella `postman/`.

## Stato

- [x] Setup progetto e configurazione
- [x] Endpoint di prova `GET /hello`
- [ ] Entity e repository
- [ ] Autenticazione JWT e ruoli
- [ ] Endpoint REST e query
- [ ] Integrazioni esterne
- [ ] Collection Postman
