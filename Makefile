.PHONY: clean clean-test clean-pyc clean-build docs help
.DEFAULT_GOAL := help
define BROWSER_PYSCRIPT
import os, webbrowser, sys
try:
	from urllib import pathname2url
except:
	from urllib.request import pathname2url

webbrowser.open("file://" + pathname2url(os.path.abspath(sys.argv[1])))
endef
export BROWSER_PYSCRIPT

define PRINT_HELP_PYSCRIPT
import re, sys

for line in sys.stdin:
	match = re.match(r'^([a-zA-Z_-]+):.*?## (.*)$$', line)
	if match:
		target, help = match.groups()
		print("%-20s %s" % (target, help))
endef
export PRINT_HELP_PYSCRIPT
BROWSER := python -c "$$BROWSER_PYSCRIPT"

help:
	@python -c "$$PRINT_HELP_PYSCRIPT" < $(MAKEFILE_LIST)

clean: ## run mvn clean
	mvn clean

lint: ## check style with checkstyle:checkstyle
	mvn checkstyle:checkstyle

test: ## run tests with mvn test
	mvn test

coverage: ## check code coverage with jacoco
	mvn test jacoco:report
	$(BROWSER) target/site/jacoco/index.html

install: clean ## install the package to local repo
	mvn install

updateversion: ## updates version in pom.xml via maven command
	mvn versions:set

runwar: install ## Builds war file and runs webapp via Jetty
	mvn jetty:run-war

javadocs: ## Generates javadoc documentation and launches browser
	mvn javadoc:javadoc
	$(BROWSER) target/site/apidocs/index.html

installdependencies: ## For running on travis, checks out and builds dependencies
	mkdir -p target/tmp
	git clone --branch=v2.4.3 --depth=1 https://github.com/ndexbio/ndex-object-model target/tmp/ndex-object-model
	cd target/tmp/ndex-object-model ; mvn clean install -DskipTests=true -B -q
	git clone --branch=master --depth=1 https://github.com/ndexbio/ndex-enrichment-rest-model target/tmp/ndex-enrichment-rest-model
	cd target/tmp/ndex-enrichment-rest-model ; mvn clean install -DskipTests=true -B -q
	git clone --branch=v2.4.3 --depth=1 https://github.com/ndexbio/ndex-java-client target/tmp/ndex-java-client
	cd target/tmp/ndex-java-client ; mvn clean install -DskipTests=true -B -q
	git clone --branch=master --depth=1 https://github.com/ndexbio/ndex-interactome-search target/tmp/ndex-interactome-search
	cd target/tmp/ndex-interactome-search ; mvn clean install -DskipTests=true -B -q
	git clone --branch=master --depth=1 https://github.com/ndexbio/ndex-enrichment-rest-client target/tmp/ndex-enrichment-rest-client
	cd target/tmp/ndex-enrichment-rest-client ; mvn clean install -DskipTests=true -B -q
	rm -rf target/tmp

