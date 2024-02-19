# ST0496-TerraViva
Progetto sviluppato dagli studenti [Daniele Monaldi](https://github.com/danielemonaldi), [Andrea Malloni](https://github.com/AndreaMalloni) e [Francesca Morici](https://github.com/Frangiosc) per il conseguimento dell'esame di **Ingegneria del Software** del corso di Informatica per la comunicazione digitale (A.A. 2023-2024).

## Descrizione del progetto
Il progetto realizzato è una piattaforma di valorizzazione territoriale per comuni italiani. La piattaforma consente, a residenti del territorio e non, di contribuire alla valorizzaione del territorio tramite la pubblicazione di contenuti di tipo informativo, culturale o semplicemente turistico, l'evidenziazione di luoghi del territorio di particolare interesse/valore e attraverso la pubblicazione di itinerari di visita locale personalizzati.
Si tenga presente che la piattaforma è stata realizzata in maniera semplicistica, tenendo in considerazione la possibilita' di gestire **un singolo** territorio comunale.

## Tecnologie e Strumenti
### Progettazione
Lo sviluppo del progetto ha seguito la struttura definita dal **Processo Unificato**, quindi svolgendo iterazioni della durata media di **3 settimane** ed utilizzando lo strumento di modellazione [Visual Paradigm](https://www.visual-paradigm.com/) per la realizzaizone dei vari modelli UML necessari alla progettazione della piattaforma.

### Implementazione
A livello di implementazione della piattaforma, il linguaggio di programmazione interamente utilizzato è stato **Java**, tramite il framework **Springboot** e **Hibernate**, che hanno permesso la realizzazione delle funzionalità deifnite in fase di progettazione e l'implementaazione di una base di dati per la memorizzazione delle informazioni della piattaforma attraverso la mappatura delle classi dei modelli.
Si tenga presente che il codice prodotto è inerente unicamente alla parte **backend** della piattaforma, come forma di interfacciamento infatti sono state adottate delle **API REST**.

## Autenticazione
Per gestire la sicurezza riguardo l'autenticazione degli utenti della piattaforma viene utilizzata la tecnologia dei token **JWT** (_JSON Web Token_). Il token JWT è un oggetto JSON che viene firmato digitalmente e contenere informazioni sull'utente autenticato, in particolare l'identità dell'utente e le autorizzazioni associate. Questo token viene generato dal server dopo che l'utente ha fornito le proprie credenziali di accesso (nome utente e password) e ha superato con successo il processo di autenticazione. Una volta generato, il token JWT viene inviato al client e memorizzato lato client.

## Autorizzazione
La piattaforma implementa un sistema di gestione degli utenti basato su ruoli, che consente di definire distinti livelli di autorizzazione per gli utenti. Ogni ruolo utente corrisponde a un insieme specifico di privilegi e permessi che determinano le operazioni che l'utente può effettuare all'interno della piattaforma.

Per implementare questa autorizzazione basata sui ruoli, le rotte Springboot sono configurate per consentire l'accesso solo a determinati tipi di utenti. La configurazione è presente all'interno del file:

    src\main\java\it\unicam\cs\ids\TerraViva\Security\SecurityConfig.java

## Configurazione
Per questioni di sicurezza, non è stato incluso nella repository il file **application.properties**, contenente alcuni parametri necessari per il corretto funzionamento dell'applicazione.

Il contenuto del file è il seguente:

    spring.datasource.url=jdbc:h2:file:./data/TerraVivaDB
    spring.h2.console.enabled=true
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=user
    spring.datasource.password=pass
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto=update
    app.jwt.expiration=86400000
    app.jwt.key=3NzNBy7UarNjkn7Q6ZBdjrtbqjz+cs1FbpnY4j+okO3WpDh89Ped6JYxZhq9e9br
    src/main/resources/application.properties

Il file va creato manualmente all'interno del seguente percorso, con il nome **application.properties**:

    src/main/resources/