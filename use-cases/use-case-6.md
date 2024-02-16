# USE CASE: 6 Produce a Report of The top  `N`  populated countries in a region
## CHARACTERISTIC INFORMATION
### Goal in Context
As an employee I want a report of all The top  `N`  populated countries in a region

### Scope
Company.

### Level
Primary task.

### Preconditions
Database contains current world population data.

### Success End Condition
A report is available for employee to provide to the organisation.

### Failed End Condition
No report is produced.

### Primary Actor
Employee.

### Trigger
A request for population information is sent to the employee.

## MAIN SUCCESS SCENARIO
1. Employee selects report type.
2. Employee selects Region.
3. Employee inputs Value`N` for populated countries in a region.
4. Employee extracts the result.
5. Employee provides the data to the organization.
## EXTENSIONS
Data is not available:
Employee informs organization that no data available.
## SUB-VARIATIONS
None.

## SCHEDULE
DUE DATE: Release 1.0