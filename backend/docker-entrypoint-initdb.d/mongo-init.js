print('Start #################################################################');

db = db.getSiblingDB('preter_dev_db');
db.createUser(
    {
        user: 'api_user',
        pwd: 'api1234',
        roles: [{ role: 'readWrite', db: 'api_dev_db' }],
    },
);
db.createCollection('articles');
db.createCollection('persons');


print('END #################################################################');