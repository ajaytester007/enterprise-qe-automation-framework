# 5-Minute Interview Walkthrough Script

## 1. Open with framework ownership
"This framework demonstrates how I structure enterprise automation for maintainability, scalability, reliability, and fast feedback. It is not just UI scripts; it is layered validation across UI, API, and backend SQL."

## 2. Explain architecture
- `pages`: Page Object Model and resilient locators
- `utils`: explicit waits, quick waits, screenshot/log helpers
- `data`: data builders for test isolation
- `api`: REST Assured clients and request/response capture
- `db`: SQL utility and reconciliation queries
- `tests`: TestNG groups for smoke, regression, API, DB
- `.github/workflows` and `Jenkinsfile`: CI/CD orchestration

## 3. Explain deterministic execution
"I reduce flakiness by avoiding Thread.sleep, using explicit waits, stable data-test locators, independent test data, and moving business validation closer to APIs/services when possible."

## 4. Explain UI vs API vs DB layering
- UI: confirms critical user path
- API: validates business rules faster and more reliably
- DB: validates financial correctness and reconciliation

## 5. Explain CI/CD quality gates
"The CI pipeline runs smoke first, then API and DB checks. Release readiness is based on pass/fail evidence, reports, logs, and artifacts."

## 6. Close with business value
"For lending payments, this matters because quality failures are not only functional issues; they can become financial accuracy, compliance, and customer-trust risks."
