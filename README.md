# Customer Mailing List Provisioning Service

Service to provisioning contacts for customer services to Google Apps mailing list.

## Config
| Key                                                          | Description                                                      | Default                                 |
|--------------------------------------------------------------|------------------------------------------------------------------|-----------------------------------------|
| fint.customer.mailinglist.provisioning.mailing-list          | Email of the mailing list. E.g. customers@mydomain.com           |                                         |
| fint.customer.mailinglist.provisioning.service-account-user  | Google users to act on behalf of. E.g. provisioning@mydomain.com |                                         |
| fint.customer.mailinglist.provisioning.credentials           | Path to credentials file.                                        | creds.json                               |
| fint.customer.mailinglist.provisioning.app-name              | Name of the app.                                                 | FINT customer mailing list provisioning |
| fint.customer.mailinglist.provisioning.interval              | Interval for provisioning in milliseconds.                                       | 50000                                    |

See [fint-portal-api](https://github.com/FINTLabs/fint-portal-api) for portal api config.

