## Setup

1. Install the [live-reload browser plugin](http://livereload.com/). Enable it
   on https://localhost:8080 (or wherever)

### Spring Application

1. Add the spring boot devtools dependency

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

1. Add the following to your `pom.xml`:

    ```xml
    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
            ...
        </plugins>
        ...
    </build>
    ```

1. Run your app with maven wrapper from the command line:

    ```
    ./mvnw spring-boot:run
    ```

    Now, in addition to restarting the application when the project is re-built,
    the project will be reloaded when files in `resources/static` or
    `resources/templates` are changed

### `npm` / webpack

1. Setup the project

    ```
    npm init -y
    npm install webpack ... # whatever other dependencies you need
    ```

1. Make a home for our (untranspiled) javascript code

    ```
    mkdir src/main/resources/javascript
    touch src/main/resources/javascript/index.js
    ```

1. Configure webpack

    Note the entry and output file paths

    ```js
    // webpack.config.js
    // ...
    entry: './src/main/javascript/index.js',
    // ...
    output: {
      path: path.resolve(__dirname, 'src/main/resources/static'),
      filename: 'main.js'
    },
    // ...
    ```

1. Add a couple build scripts

    package.json

    ```json
    {
        ...
        "scripts": {
            "build": "webpack -p",
            "watch": "webpack --watch"
        }
        ...
    }
    ```

1. Ignore the generated files

    `.gitignore`

    ```
    ...
    node_modules/
    src/main/resources/static/main.js
    *.map
    ...
    ```

    Just like we don't commit `.class` files, the `main.js` file is also a build
    artifact, not a source code file.

### Bring It All Together

1. Start up the spring boot app and watch the js sources for changes. In two
   seperate terminals:

    ```
    npm run watch
    ```

    ```
    ./mvnw spring-boot:run
    ```

1. Edit files and see the changes live!

---

When you edit your js source, webpack will automatically transpile the code and
put it in `resources/static`, which will cause the spring app to live reload.
Similarly, editing template files (`resources/templates`) will cause the spring
app to live reload as well.

If you change any java source code, you should just build the project, and the
dev tools will restart the application for you (this is faster than manually
restarting the app.)
