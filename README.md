# Module Identity Picker - Appointment

Module Lutece qui intègre le widget Identity Picker dans le plugin Appointment (RDV).

Il permet de rechercher et pré-remplir les données d'identité d'un usager lors de la prise de rendez-vous en back-office.

## Fonctionnalités

- **Recherche d'identité** sur le formulaire de prise de RDV : le widget Identity Picker remplit automatiquement les champs nom, prénom, email et confirmation email
- **Entry type Generic Attributes** : un type d'entrée « Identity Picker » configurable par l'administrateur, permettant de sélectionner les attributs d'identité à afficher, avec les options de création/modification d'identité et de stockage des données
- **Affichage des attributs en français** dans les formulaires et la vue détail d'un RDV (noms récupérés depuis l'Identity Store)
- **Vue détail RDV enrichie** : override du template `view_appointment.html` pour afficher le widget Identity Picker en lecture seule avec les données de l'usager

## Prérequis

| Dépendance | Version |
|---|---|
| lutece-core | [7.0.0, 7.9.9) |
| plugin-appointment | [3.0.11, 3.9.9) |
| plugin-identitypicker | [1.0.0-SNAPSHOT, 1.9.9) |
| module-genericattributes-identitypicker | [1.0.0-SNAPSHOT, 1.9.9) |

La propriété `identitypicker.default.client.code` doit être configurée avec le code client de l'Identity Store.

## Build

```bash
# Compilation (sans tests)
mvn clean install -DskipTests

# Compilation avec tests (nécessite la base Lutece de test)
mvn clean install
```

## Installation

1. Ajouter la dépendance Maven dans le site Lutece
2. Exécuter le script SQL d'initialisation pour enregistrer le type d'entrée :
   ```
   src/sql/plugins/genericattributes/plugin/init_db_genericattributes_identitypicker-appointment.sql
   ```
3. Redémarrer l'application

## Configuration de l'entry type

Dans le back-office, lors de la configuration d'un formulaire de RDV :

1. Ajouter une entrée de type **Identity Picker**
2. Sélectionner les attributs d'identité souhaités (nom, prénom, email, etc.)
3. Configurer les options :
   - **Créer identité** : autoriser la création de nouvelles identités
   - **Modifier identité** : autoriser la modification d'identités existantes
4. Enregistrer l'entrée

Les noms d'attributs sont automatiquement récupérés depuis l'Identity Store et affichés en français.

## Templates overridés

Ce module override les templates suivants du plugin Appointment :

| Template | Description |
|---|---|
| `admin/.../html_code_form.html` | Formulaire de RDV back-office — ajout du widget Identity Picker avec mapping vers les champs nom/prénom/email |
| `admin/.../appointment/view_appointment.html` | Vue détail d'un RDV — affichage enrichi de l'identité via le widget en lecture seule |

## Architecture

```
src/java/.../service/entrytype/
  └── EntryTypeIdentityPicker.java    # Entry type pour appointment, étend AbstractEntryTypeIdentityPicker

webapp/WEB-INF/
  ├── conf/plugins/
  │   ├── appointment_idpicker_context.xml   # Bean Spring
  │   └── appointment_idpicker.properties
  ├── plugins/
  │   └── appointment_idpicker.xml           # Descripteur plugin
  └── templates/
      └── admin/plugins/appointment/
          ├── html_code_form.html                              # Override formulaire BO
          ├── appointment/view_appointment.html                # Override vue détail RDV
          └── entries/
              ├── create_entry_type_identity_picker.html       # Création entry
              ├── modify_entry_type_identity_picker.html       # Modification entry
              ├── html_code_entry_type_identity_picker.html    # Rendu BO (mode stockage)
              ├── html_code_entry_type_identity_picker_only_cuid.html  # Rendu BO (CUID seul)
              └── readonly_entry_type_identity_picker.html     # Lecture seule BO
```
