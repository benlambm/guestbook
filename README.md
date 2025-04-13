# RESTful Guestbook Demo

Welcome! This is a simple Spring Boot Guestbook application deployed live on AWS, demonstrating a basic CI/CD pipeline.

**Live URL:** [http://guestbook-env.eba-yqpn5naf.us-east-1.elasticbeanstalk.com/](http://guestbook-env.eba-yqpn5naf.us-east-1.elasticbeanstalk.com/)

**Extra Credit Opportunities:**

1.  **Sign & Comment:**
    * Sign the guestbook using the REST API instructions on the main page.
    * Visit your personal guest page (click your name on the list).
    * Leave **two** comments on your page using the REST API instructions provided there.

2.  **Contribute via Pull Request:**
    * Suggest a small enhancement or fix for the application (e.g., improving styling, adding a feature, fixing a typo).
    * Create a **Pull Request (PR)** against the main GitHub repository with your proposed changes.

## How This App Demonstrates CI/CD

This Java Spring Boot application is a live example of Continuous Integration and Continuous Deployment (CI/CD). Here's what happens when you push changes to the `origin/main` branch:

- **Local Changes:** You develop and push your code locally.
- **GitHub Actions:** Your push triggers a GitHub Actions workflow.
- **Maven Build:** The workflow runs Maven to build the application.
- **AWS Elastic Beanstalk Deployment:** The built app is automatically deployed to AWS Elastic Beanstalk, updating the live web app.

Every update flows from code commit to live deployment without manual intervention—showing the true power of CI/CD!
