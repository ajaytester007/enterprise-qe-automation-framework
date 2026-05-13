# PNC Lending Payments QE Framework - Interview Walkthrough

Enterprise-style Java automation framework demonstrating framework ownership for a Senior Quality Engineer role on a lending/payments platform.

## What this demonstrates
- Selenium WebDriver UI automation using Page Object Model
- Java + TestNG execution model
- Cucumber BDD executable specifications
- REST Assured API/service-layer validation
- SQL backend reconciliation using H2 demo database
- Deterministic waits and resilient locator strategy
- Reusable data builders, common wrappers, helpers, and utilities
- Test isolation and controlled test data strategies
- Actionable diagnostics: screenshots, logs, request/response capture, failure traces
- Parallel and selective test execution using TestNG groups and Maven profiles
- CI/CD orchestration with GitHub Actions and Jenkinsfile
- Release-readiness quality gates and evidence-based reporting

## Interview narrative
"I use this framework to explain how I think about automation ownership. The goal is not just test execution. The goal is reliable, deterministic, maintainable validation across UI, API, and backend data layers so the delivery team can make evidence-based release decisions."

## Architecture
```text
src/test/java/com/hashtagash/pnc/framework
  api/            REST Assured clients, request/response capture
  config/         centralized environment and framework settings
  core/           driver factory, base test, listener, diagnostics
  data/           reusable builders for controlled test data
  db/             SQL connection and reconciliation utilities
  pages/          Page Objects with resilient locators
  steps/          Cucumber step definitions
  tests/          TestNG smoke/regression/API/DB tests
  utils/          waits, screenshots, logging, JSON helpers
src/test/resources
  features/       BDD executable specifications
  sql/            SQL validation examples and seed data
  config.properties
.github/workflows/ci.yml
Jenkinsfile
pom.xml
```

## Run commands
```bash
mvn clean test -Dgroups=smoke -Dheadless=true
mvn clean test -Dgroups=api,db
mvn clean test -DsuiteXmlFile=testng-parallel.xml
mvn clean test -Dcucumber.filter.tags="@payments and @regression"
```

## Quality gates
- Smoke suite must pass 100%
- Critical payment API tests must pass 100%
- No Sev-1/Sev-2 open defects for release candidate
- Regression pass rate threshold: 95%+
- Flaky test quarantine requires RCA and owner
- CI artifacts include logs, screenshots, request/response captures, SQL evidence

## How this maps to the PNC role
- Lending payments: demonstrates money movement, loan payment, posting, reconciliation, duplicate prevention, and audit-style validation patterns.
- API-first validation: business rules validated at service layer where feedback is faster and more deterministic.
- Selective UI validation: UI tests cover critical workflows without making the suite brittle.
- Backend data verification: SQL examples show transaction integrity and reconciliation controls.
- CI/CD: Maven, TestNG groups, GitHub Actions, and Jenkinsfile support release-readiness execution.
