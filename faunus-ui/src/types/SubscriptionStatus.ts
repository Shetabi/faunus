export enum WateringSubscriptionStatus {
    SUBSCRIBED = "SUBSCRIBED",
    UN_SUBSCRIBED = "UN_SUBSCRIBED",
}

export interface SubscriptionStatusDTO {
    status: WateringSubscriptionStatus;
}

export interface NotificationRequest {
    token: string;
}