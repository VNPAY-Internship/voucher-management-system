# Vouching-management-system

After decade of evolving, e-commerce involving deeply into people’s lives around the world. The competition between giant techs getting more intense, and Voucher is one of the biggest “weapons” in this battle.  The goals of this project are building an application to create and deliver the voucher to the end users. We are living in the digital world, so all of the voucher code and redeemed by the users must be tracked for the further use. This application must guarantee the performance even in the peak time, one service slow means we are losing the customers. 

## Architecture:
Consider employing a microservices architecture. This architecture type breaks down your application into small, autonomous services which are easier to maintain and scale independently. Given that performance is crucial, especially during peak times, microservices can facilitate better load distribution and failure isolation.

## Components:
1. User Service: Handles user data and user authorization levels. This microservice will be responsible for user registration, authentication and role-based access control.

2. Campaign Service: Responsible for creating and managing campaigns. It should handle creating campaigns, assigning vouchers to campaigns, and ending campaigns.

3. Voucher Service: Handles the creation of voucher codes, assigning them to specific campaigns, and managing their redemption status. This service should also facilitate various promotion types like flash sales vouchers, gift cards, etc.

4. Delivery Service: Manages the delivery of voucher codes to end users through different channels (e.g., email, SMS, push notifications).

5. Reporting Service: Generates reports and analytics on campaign effectiveness, with graphical representations. This service should interact with other services to gather the necessary data.

6. API Gateway: This acts as a single entry point for all the client requests. It directs requests to appropriate microservices and is beneficial for load balancing, security (such as rate limiting), and can aggregate responses from different microservices.

7. Notification Service (optional): If the system needs to notify users or admins about the status of campaigns or vouchers, it can be handled by a dedicated notification service.
