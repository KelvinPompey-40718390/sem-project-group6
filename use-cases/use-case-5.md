# USE CASE: 5 Generate a report to show the top `N` populated countries in a continent where `N` is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context


Retrieve Top N Populated Countries in a Continent.

### Scope
Organisation.

### Level

Primary task.

### Preconditions

We are aware of the list of countries and their populations.

### Success End Condition

The user receives a report based on their query

### Failed End Condition

No report is generated

### Primary Actor

A user within the organization

### Trigger

A user is required to produce a report

## MAIN SUCCESS SCENARIO

1. User selects report option.
2. User selects a continent.
3. User enters a value for `N`.
4. A report is produced for the selected continent; sorted Largest to Smallest population.


## EXTENSIONS

2. **User enters invalid value**
    1. User is prompted to enter a correct value, `N`.
   
## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0