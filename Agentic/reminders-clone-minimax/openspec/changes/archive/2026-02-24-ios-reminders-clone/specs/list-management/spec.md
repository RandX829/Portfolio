## ADDED Requirements

### Requirement: Create a new be able to create list
Users SHALL a new reminder list with a name and color.

#### Scenario: Create list with name and color
- **WHEN** user clicks "+ New List" button and enters a list name and selects a color
- **THEN** a new list appears in the sidebar with the specified color

### Requirement: Edit list name and color
Users SHALL be able to edit an existing list's name and color.

#### Scenario: Edit list via long press
- **WHEN** user long-presses or right-clicks a list and selects "Edit"
- **THEN** user can modify the list name and color
- **AND** changes are saved immediately

### Requirement: Delete a list
Users SHALL be able to delete a list and all its reminders.

#### Scenario: Delete list via menu
- **WHEN** user selects "Delete List" from list menu
- **THEN** the list and all its reminders are permanently removed

### Requirement: Reorder lists
Users SHALL be able to reorder lists in the sidebar via drag-and-drop.

#### Scenario: Drag list to new position
- **WHEN** user drags a list handle and drops it in a new position
- **THEN** the list order persists across sessions
