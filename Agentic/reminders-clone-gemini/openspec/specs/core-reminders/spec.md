# Core Reminders Specification

## Overview
A reactive, local-first web application that mimics the core features of the iOS Reminders app.

## Requirements

### Lists Management
- Users can create, rename, and delete custom lists.
- Each list has a unique ID, name, and an associated color/icon.

#### Scenario: Create a new list
- **WHEN** the user provides a name and selects a color/icon
- **THEN** a new list is created and appears in the sidebar

### Smart Lists
- Auto-generated lists based on filter criteria:
    - **Today**: Reminders due today.
    - **Scheduled**: Reminders with a due date.
    - **All**: Every active reminder.
    - **Completed**: Every finished reminder.

#### Scenario: View Today's reminders
- **WHEN** the user selects the "Today" smart list
- **THEN** all reminders with a due date of today are displayed

### Reminders Management
- **Title**: Required, editable inline.
- **Notes**: Optional multi-line text.
- **Due Date**: Optional date picker support.
- **Priority**: Four levels (None, Low, Medium, High).
- **Completion**: Toggle checkbox to mark as done/undone.

#### Scenario: Mark reminder as complete
- **WHEN** the user clicks the checkbox next to a reminder
- **THEN** the reminder's status is updated to completed and its visual style changes

### Search and Filtering
- Search bar to filter reminders across all lists by title or notes.

## User Interface Guidelines
- **Premium Aesthetic**: High-contrast typography, rounded corners, subtle shadows, and Apple-standard colors.
- **Responsive Layout**: Adapts from a multi-column desktop view to a single-column mobile view.
