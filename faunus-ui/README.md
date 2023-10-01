# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `make run`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `make test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.


## Package structure

```bash
/src
  /components
    /Common
      - Header.js
      - Footer.js
    /Auth
      - LoginForm.js
      - RegistrationForm.js
    /Dashboard
      - Dashboard.js
      - Widget.js
  /containers
    /Public
      - Home.js
      - About.js
    /Private
      - DashboardPage.js
      - ProfilePage.js
  /context
    - AuthContext.js
  /hooks
    - useFetch.js
  /services
    - authService.js
    - apiService.js
  /utils
    - helpers.js
  /styles
    - main.css
  /assets
    /images
    /fonts
  /routes
    - AppRouter.js
  /redux
    /actions
    /reducers
    /store.js
  /config
    - config.js
  /tests
  - index.js
  - App.js
  - index.css
  - setupTests.js

```

Here's a breakdown of each directory in this suggested structure:

- components: This is where you store your reusable UI components. You can further organize them by creating subdirectories for common components and components related to specific parts of your application.

- containers: Containers are higher-level components that connect to your Redux store or manage state. Organize them based on whether they are meant for public or private routes.

- context: If you're using the Context API for state management, place your context providers and consumers here.

- hooks: Custom React hooks that you create for managing state, side effects, or other functionality.

- services: Place any services responsible for handling API requests, authentication, or other external interactions.

- utils: Utility functions and helper files that don't fit neatly into components or services.

- styles: CSS or other styling files for your application.

- assets: Store static assets like images and fonts here.

- routes: Define your application's routing configuration here. You might have a single AppRouter.js file that manages routes using a library like react-router.

- redux: If you're using Redux for state management, organize your actions, reducers, and the store configuration here.

- config: Configuration files for your application, such as environment-specific settings.

- tests: Place your unit tests and integration tests here, organized by component or feature.

- index.js: The entry point of your application, where you typically render the root component and set up Redux or any other context providers.

- App.js: The main component where you define the structure of your application, including routing.

- index.css: Global styles for your application.

- setupTests.js: Configuration for test setup.
