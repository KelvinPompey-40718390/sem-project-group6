# USE CASE: 21 Generate a report to show the top `N` populated capital cities in a continent where `N` is provided by the user.
## CHARACTERISTIC INFORMATION

### Goal in Context

As a user I want to view the top `N` populated capital cities in a continent where `N` is provided by myself.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database already exists with the population information.

### Success End Condition

The User can select a continent, enter a value for N and see the Top N populated cities for the selected continent. 

### Failed End Condition

No Report is produced.

### Primary Actor

User

### Trigger

A request for the top N populated cities on a continent is sent to the user.

## MAIN SUCCESS SCENARIO

1. User selects report type.
2. User selects a continent.
3. User enters a value for `N`.
4. A Report is produced for the selected continent; sorted Largest to Smallest population.
5. User provides the data to the organisation.

## EXTENSIONS

3. **User Enters invalid value for `N`**:
   1. User asked to enter a value for `N`
4. **No data error**:
   1. User informs the organization that no data is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0