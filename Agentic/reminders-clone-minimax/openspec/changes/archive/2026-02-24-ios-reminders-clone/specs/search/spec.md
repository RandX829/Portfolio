## ADDED Requirements

### Requirement: Search reminders
Users SHALL be able to search across all reminders by title and notes.

#### Scenario: Search by title
- **WHEN** user types in the search field
- **THEN** reminders matching the search term (case-insensitive) are shown
- **AND** matches are highlighted in the results

#### Scenario: Search by notes
- **WHEN** user searches for text that appears in a reminder's notes
- **THEN** that reminder appears in the search results

#### Scenario: Search clears on escape
- **WHEN** user presses the Escape key while searching
- **THEN** the search field is cleared
- **AND** the current view (Today/All/etc.) is restored

### Requirement: Search results show context
Search results SHALL show which list each reminder belongs to.

#### Scenario: Search shows list context
- **WHEN** user sees search results
- **THEN** each result displays the list name and color indicator
- **AND** user can tap a result to navigate to that reminder
