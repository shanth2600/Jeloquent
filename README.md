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
User user = new User();
ArrayList<Map> users = user.all();
```
result:
```
{last_name=Bags, id=1, first_name=Jimmy, email=Jimmy@gmail.com}
{last_name=Bones, id=1, first_name=Johnny, email=johnny@gmail.com}
{last_name=Bilson, id=1, first_name=James, email=james@gmail.com}
{last_name=Bosley, id=1, first_name=Jedediah, email=Jeb@gmail.com}
{last_name=Bigs, id=1, first_name=Jeff, email=null}
```

# Get All Users With Posts and Profile Eager Loaded
```
User user = new User();
ArrayList<HashMap> users = user.with(new String[]{"posts","profiles"}).all();
```
result:
```
{post=[{title="i hate beachers",}], profile=[{bio=Hi there!, id=1}], last_name=Bags, id=1, first_name=Jimmy, email=jimmy@gmail.com}
{post=[], profile=[{bio=Hello!, id=2}], last_name=Bones, id=2, first_name=johnny, email=johnny@gmail.com}
{post=[], profile=[], last_name=Bilson, id=3, first_name=James, email=james@gmail.com}
{post=[], profile=[], last_name=Bosley, id=4, first_name=Jebdediah, email=jeb@gmail.com}
{post=[], profile=[], last_name=Bigs, id=5, first_name=Jeff, email=}
```

# Creating a User

```
User user = new User();
Map fields = new HashMap<>();
fields.put("first_name","jimmy");
fields.put("last_name","johns");

user.create(fields);

```

result:

![Alt text](http://i.imgur.com/TEfFXru.png "Optional title")


# Editing a User
```
int id = 1;
User user = new User();
Map fields = new HashMap<>();
fields.put("first_name","Johnny");
fields.put("last_name","Jims");
user.update(id,fields);
```
result:

![Alt text](http://i.imgur.com/bN6oDBu.png "Optional title")



# Finding a User
```
int id = 1;
User user = new User();
user.find(id);

```
result:
```
{last_name=Bags, id=1, first_name=Jimmy, email=Jimmy@gmail.com}
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
