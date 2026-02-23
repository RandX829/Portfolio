## ADDED Requirements

### Requirement: Local Persistence
State is saved locally to ensure data persists across browser refreshes.

#### Scenario: Auto-save state
- **WHEN** any change is made to lists or reminders
- **THEN** the entire application state is serialized and saved to `localStorage`

### Requirement: JSON Export
Users can download their data as a JSON file.

#### Scenario: Export data
- **WHEN** the user clicks "Export Data"
- **THEN** a `.json` file containing the current state is downloaded to their machine

### Requirement: JSON Import
Users can upload a JSON file to restore their data.

#### Scenario: Import valid data
- **WHEN** the user uploads a valid JSON file
- **THEN** the application state is updated with the new data and saved to `localStorage`
