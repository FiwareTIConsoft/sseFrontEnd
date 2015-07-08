#!/bin/bash
curl "http://ca.tilab.com/builds/SSEFrontend/last/ssefrontend-install-package.noarch.rpm" > "/tmp/ssefrontend-install-package.noarch.rpm"
rpm -ev ssefrontend-install-package.noarch
rpm -ivh /tmp/ssefrontend-install-package.noarch.rpm
echo "ssefrontend installed"
