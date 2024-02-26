# USE CASE: 6 Produce a Report of The top  `N`  populated countries in a region
## CHARACTERISTIC INFORMATION
### Goal in Context
As an employee I want a report of all the top  `N`  populated countries in a region

### Scope
Organisation.

### Level
Primary task.

### Preconditions
Database contains current world population data.

### Success End Condition
A report is available for the user to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
User.

### Trigger
A request for population information is sent to the user.

## MAIN SUCCESS SCENARIO
1. User selects report type.
2. User selects Region.
3. User inputs Value `N` for populated countries in a region.
4. A report is generated to determine the top `N` populated countries in the selected region.
5. User provides the data to the organization.

## EXTENSIONS
3. **User enters invalid value**:
    1. User is prompted to enter a correct value, `N`.
4. **Data is not available**:
   1. Employee informs organization that no data available.
   
## SUB-VARIATIONS
None.

## SCHEDULE
DUE DATE: Release 1.0