# pt15-intranet-bioweb-pacients

## ESTAT VERSIÓ: 
Plantilla PT15, el login està fet. 

## REQUERIMENTS
* Desenvolupament multiplataforma, prioritzant l'ús de Linux Ubuntu 20.04
* IDE amb suport per Java → Apache Netbeans 12, mínim versió 12.0
* JDK → Open JDK 11 actualitzada. També pot ser necessari la JDK 8 actualitzada.
* Servidor web configurat amb Java → Apache Tomcat 9.0.20 o Glassfish 5.0.11
* Llibreria JavaEE Web 7 (Apache Netbeans la inclou)
* Llibreries JUnit 4 si volem fer testing avançat (Apache Netbeans la inclou)
* Llibraria generació fitxers JSON (SimpleJSON o GSON) per tractar estructures i facilitar creació Web Services.

## DESCRIPCIÓ DEL PROJECTE:

L’ICS ens ha demanat la segona fase d’una aplicació web per a gestionar dades bàsiques de pacients de la secció d’oncologia. 
Volen un portal web amb una intranet on només es podran connectar els usuaris autoritzats. 

Els interessa tenir aquestes funcionalitats concretes. 

Necessiten una aplicació web senzilla; amb 3 opcions de menú:

### Estudi de Cadenes d’ADN.
Una pàgina que, donada un tros de cadena d’ADN de com a molt 1000 caràcters, verifiqui si és vàlida i mostri el número de A, G, C i T de l’ADN passat per pantalla. 
Cal informar a l’usuari si no ha inserit una cadena ADN correcta.

### Llistat de pacients.
Una pàgina que mostrarà el tot llistat de pacients en una taula. Cal tenir-ne 4 de inserits inicialment. 

### Filtre de pacients per un o 2 criteris.
Una pàgina que mostrarà llistat de pacients en una taula, i permetrà filtrar-lo per algún camp rellevant: RH, Gènere.
Amb 1 o 2 criteris és suficient.
Hi ha 2 formes per a pintar els resultats:
* Des d'un ArrayList<Patient> extret d'una JSP.
* Des d'un JSON, així no cal usar una JSP per a pintar els resultats.
    
### OPCIONAL. Filtre de pacients per cognom i/o nom, amb un camp autoemplenable.

### Afegir pacient (només usuaris admin)
Una pàgina amb un formulari on l’usuari podrà omplir les dades d’un pacient i guardar-lo al sistema. 
Cal informar a l’usuari si ha pogut registrar el pacient o bé no ha omplert tots els camps correctament. 

### Llistat d'usuaris (només usuaris admin)
Una pàgina on es mostri el llistat d'usuaris. 


## Observacions importants.

Les dades dels pacients, ara per ara, seran:

#### Nom → text
#### Cognoms → text 
#### Genere (home, done, NC) → select o option
#### GrupSanguini→ select o option
#### RH → select o option
#### pes → text, validació número
#### alcada → text, validació número

Les dades en aquesta fase no seran persistents.
Per ara, es guardaran en memòria mitjançant un ArrayList però es preveu que en un futur (el 2022) es gestionin en una base de dades SQL; així que es valorarà positivament usar el patró DAO per a poder fer el canvi d’escenari fàcilment.

## Algunes de les tasques que es preveu implementar en un futur.
    * Mostrar la informació dels pacients en un web service.
    * Mostrar aquesta llista amb un estil RWD, que es vegi bé en totes les pantalles. 
