# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

A small Spring MVC + Hibernate CRUD sample app for managing `Person` records (name, gender, date of birth). It originated from a 2009 Netbeans/Glassfish tutorial ("Simple CRUD - SpringMVC & Hibernate3") and has been incrementally upgraded in place since (Spring 3.2 → 6.2, Hibernate 3 → 6, Java 8 → 21, mysql-connector 5 → 9 — see git log). There is no business logic beyond basic CRUD; treat this as a reference/training app, not a product.

## Build, test, run

JDK 21 is required (`maven.compiler.release=21` in `pom.xml`) but is **not** the default JDK on this machine — the environment's `JAVA_HOME` points at JDK 11. Point Maven at a JDK 21 install (e.g. `C:\Program Files\Java\jdk-21`) before building.

- Build + full verify (what CI runs): `mvn clean verify`
- Compile only: `mvn compile`
- Run all tests: `mvn test`
- Run a single test class: `mvn test -Dtest=PersonControllersTest`
- Run a single test method: `mvn test -Dtest=PersonControllersTest#indexTest`
- Package the WAR: `mvn clean package` → `target/person-sample-webapp.war`
- JaCoCo coverage runs automatically during `verify` (bound to `prepare-package`); report lands in `target/site/jacoco/`.

### Database

There is no in-memory DB and no mocking of Hibernate/Spring — `PersonControllersTest` boots the real Spring `applicationContext.xml` and talks to an actual MySQL instance. Before building or testing:

1. Load `database/ddl_dml.sql` into MySQL — it drops/recreates the `crud` database, creates `tbl_person`, and inserts sample rows.
2. Make sure MySQL is reachable at the coordinates in `src/main/webapp/WEB-INF/jdbc.properties` (currently `jdbc:mysql://localhost:3310/crud`, user `travis`, no password).

### Running the app

Packaged as a WAR with no embedded server plugin in `pom.xml`. Locally it's launched via the IntelliJ SmartTomcat plugin (config under `.smarttomcat/person/`), serving on port 8282. Without SmartTomcat, deploy the WAR to any Servlet 6.1 container.

## Architecture

Classic Spring MVC + Hibernate 3-tier CRUD, wired almost entirely through XML (no `@Configuration`, no Spring Boot):

- `web.xml` loads `applicationContext.xml` as the **root** context (datasource, Hibernate `SessionFactory`, transaction manager, service/DAO beans) via `ContextLoaderListener`, and maps `DispatcherServlet` to `*.html`. The dispatcher loads `dispatcher-servlet.xml` for the view resolver and per-URL controller beans.
- **Controllers** (`controller/`): one class per URL, registered by bean name in `dispatcher-servlet.xml` (e.g. bean `name="/view.html"` → `ViewPersonController`). Most implement the legacy `org.springframework.web.servlet.mvc.Controller#handleRequest`; `SavePersonController` is the one exception using modern `@Controller` + `@PostMapping`. Match whichever style the controller you're editing already uses.
- **Service layer** (`service/`): `PersonService`/`PersonServiceImpl`. Transaction boundaries are declared in `applicationContext.xml` via `TransactionProxyFactoryBean` (read-only for `get*`, required for everything else) — not via `@Transactional`.
- **DAO layer** (`dao/`): `PersonDAO`/`PersonDAOHib` talks to Hibernate directly through an injected `SessionFactory` (no Spring Data, no generic repository).
- **Model** (`model/Person.java`): the single JPA entity, mapped to `tbl_person` and discovered via Hibernate `packagesToScan` (no `hibernate.cfg.xml`).
- **Views**: JSPs under `src/main/webapp/WEB-INF/jsp/`, resolved by `InternalResourceViewResolver`. `index.jsp` paginates results using Spring's `PagedListHolder`, which is stored in the HTTP session under the `personList` attribute. Both `IndexController` and `DeletePersonController` read and mutate this session-held pager directly — keep the two in sync if you touch list/pagination/delete behavior.

## CI

`.travis.yml` runs `mvn clean verify` then, on `master`, `sonar-scanner` against SonarCloud (project key `org.sanmarcux.samples.mysql.person`, config in `sonar-project.properties`). Travis seeds its MySQL instance from `database/ddl_dml.sql` before the build.
