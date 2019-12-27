# IFI - TP 4 - JPA & Repositories

Jayjaywantee KOODUN

_Master 2 Informatique E-Services 2019/2020_

## Lien du projet sur Heroku

S'authentifier avec le username : `user` et password : `f1d08de0-37b4-4620-9ae7-cef20e55debe`

-   GET : all trainers

[https://trainer-api-shalini.herokuapp.com/trainers/](https://trainer-api-shalini.herokuapp.com/trainers/)

-   GET : trainer ayant le nom Ash

[https://trainer-api-shalini.herokuapp.com/trainers/Ash](https://trainer-api-shalini.herokuapp.com/trainers/Ash)

-   POST : creation du trainer ayant le nom `Bug Catcher`

Tester sur POSTMAN avec la méthode `POST` sur l'url suivant : [https://trainer-api-shalini.herokuapp.com/trainers/](https://trainer-api-shalini.herokuapp.com/trainers/)

En envoyant le json suivant dans le body du request :

```json
{
    "name": "Bug Catcher",
    "password": "bug_password",
    "team": [
        {
            "pokemonType": 13,
            "level": 6
        },
        {
            "pokemonType": 10,
            "level": 6
        }
    ]
}
```

Vérifier l'ajout du trainer en testant l'url suivant : [https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher](https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher)

-   PUT : modification du trainer ayant le nom `Bug Catcher`

Tester sur POSTMAN avec la méthode `PUT` sur l'url suivant : [https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher](https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher)

En envoyant le json suivant dans le body du request :

```json
{
    "name": "Bug Catcher",
    "password": "bug_password",
    "team": [
        {
            "pokemonType": 13,
            "level": 8
        },
        {
            "pokemonType": 10,
            "level": 8
        }
    ]
}
```

Vérifier la modification du trainer en testant l'url suivant : [https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher](https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher)

-   DELETE : suppression du trainer ayant le nom `Bug Catcher`

Tester sur POSTMAN avec la méthode `DELETE` sur l'url suivant : [https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher](https://trainer-api-shalini.herokuapp.com/trainers/Bug%20Catcher)
