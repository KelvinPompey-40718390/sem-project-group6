# USE CASE: 8 Generate a Report of All the cities in a continent organised by largest population to smallest.
## CHARACTERISTIC INFORMATION
### Goal in Context
As a user I want a report of All the cities in a continent organised by largest population to smallest.

### Scope
Organisation.

### Level
Primary task.

### Preconditions
Database contains current world population data.

### Success End Condition
A report is available for employee to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
User.

### Trigger
A request for population information is sent to the User.

## MAIN SUCCESS SCENARIO
1. User selects report type.
2. User select the continent
3. A report is generated based on the report request.
4. User provides the data to the organization.

## EXTENSIONS
3. **Data is not available**:
    1. User informs organisation that no data available.

## SUB-VARIATIONS
None.

## SCHEDULE
DUE DATE: Release 1.0