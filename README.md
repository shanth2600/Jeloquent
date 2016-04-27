# Jeloquent

# Print ArrayList
```
  printList(list);
```

# Print Map
```
  printMap(map);
```

# Get All Users
```
Post user = new User();
ArrayList<Map> users = user.all();
```

# Get All Users With Posts and Profile Eager Loaded
```
User user = new User();
ArrayList<HashMap> users = user.with(new String[]{"posts","profiles"}).all();
```

# Creating a User

```
User user = new User();
Map fields = new HashMap<>();
fields.put("first_name","jimmy");
fields.put("last_name","johns");

user.create(fields);

```

# Editing a User
```
int id = 1;
User user = new User();
Map fields = new HashMap<>();
fields.put("first_name","Johnny");
fields.put("last_name","Jims");
user.update(id,fields);
```


# Finding a User
```
int id = 1;
User user = new User();
user.find(id);

```

# Finding a set of users with a specific field value
## (wheres are chainable)
```

User user = new User();
ArrayList<HashMap> list = user.where("first_name","=","shant").get();


```

# Get All Posts
```
Post post = new Post();
ArrayList<Map> posts = post.all();
```

# Get All Posts with User model eager loaded
```
Post post = new Post();
ArrayList<Map> posts = post.with(new String[]{"user"}).all();
```

# Creating a Post

```
Post post = new Post();
Map fields = new HashMap<>();
fields.put("title","Lorem");
fields.put("body","Ipsum");

post.create(fields);

```


# Editing a Post
```
int id = 1;
Post post = new Post();
Map fields = new HashMap<>();
fields.put("title","Sample");
fields.put("body","Article");
post.update(id,fields);
```
