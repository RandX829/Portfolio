# Data Persistence Specification

## Persistence Strategy
The application operates entirely on the client-side. No server-side database is required for the MVP.

### Primary Storage: localStorage
- State is serialized to JSON and persisted in `localStorage` under the key `reminders_app_data`.
- On application load, data is hydrated from `localStorage`.

#### Scenario: Auto-save state
- **WHEN** any change is made to lists or reminders
- **THEN** the entire application state is serialized and saved to `localStorage`

### Secondary Storage: JSON File Export/Import
- **Export**: Users can trigger a download of their entire state as a `.json` file.
- **Import**: Users can upload a previously exported `.json` file to restore or merge their data. Perfect for satisfying the "JSON file persistence" requirement without a backend.

#### Scenario: Export data
- **WHEN** the user clicks "Export Data"
- **THEN** a `.json` file containing the current state is downloaded to their machine

#### Scenario: Import valid data
- **WHEN** the user uploads a valid JSON file
- **THEN** the application state is updated with the new data and saved to `localStorage`

## Schema Definition (v1)

```json
{
  "version": 1,
  "settings": {
    "theme": "light",
    "user_name": "User"
  },
  "lists": [
    {
      "id": "list_uuid_1",
      "name": "Personal",
      "color": "blue",
      "icon": "user"
    }
  ],
  "reminders": [
    {
      "id": "reminder_uuid_1",
      "listId": "list_uuid_1",
      "title": "Milk",
      "notes": "Buy skimmed milk",
      "dueDate": "2026-02-18T12:00:00Z",
      "priority": "medium",
      "isCompleted": false
    }
  ]
}
```
