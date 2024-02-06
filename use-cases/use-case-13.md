# USE CASE: 13 Produce a Report of the top N populated cities in a continent where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *employee* I want *a report of the top N populated cities in a continent where N is provided by the user* to *allow easy access to population information*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current world population data.

### Success End Condition

A report is available for employee to provide to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

Employee.

### Trigger

A request for population information is sent to the employee.

## MAIN SUCCESS SCENARIO

0. User inputs N
1. Employee requests top N populated cities per continent.
3. Employee extracts the result.
4. Employee provides the data to the organization.

## EXTENSIONS

3. **Data is not available**:
    1. Employee informs organization that no data available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
