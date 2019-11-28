# Instagram-Fluent-API 

A fluent-API wrapper as a facade interface that ports to private Instagram api ([Instagram4J](https://github.com/brunocvcunha/instagram4j)). 

By making it a fluent-API, your code can be really clean and easy.

# Usage

1. Log in 

```java
Instagram ig = Instagram4JInstantiator.instantiate();
InstagramSession session = ig.login("username", "password");
```

2. Like all his recent feeds

```java
session.searchUser("username")
             .foreachFeed()
             .like();
```

or even

`session.searchUser("username").likeRecentFeeds();`

3. Comment on his first feed

```java
session.searchUser("username")
        .getFirstFeed()
        .comment("Nice picture!"));
```

4. Unfollow all friends who hasn't followed back

```java
session.searchUser("username")
            .foreachAllFollowers()
            .filter(InstagramUser::hasNotFollowMe)
            .unfollow();
```

5. Send direct message to several users

```java
session.searchUsers("username1", "username2", "username3")
            .sendDM("Hi");
```
