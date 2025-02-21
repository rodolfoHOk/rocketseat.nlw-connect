import { db } from '../drizzle/client'
import { subscriptions } from '../drizzle/schema/subscriptions'

interface SubscribeToEventParams {
  name: string
  email: string
}

export async function subscribeToEvent({
  name,
  email,
}: SubscribeToEventParams) {
  const [{ subscriberId }] = await db
    .insert(subscriptions)
    .values({
      name,
      email,
    })
    .returning({
      subscriberId: subscriptions.id,
    })

  return {
    subscriberId,
  }
}
