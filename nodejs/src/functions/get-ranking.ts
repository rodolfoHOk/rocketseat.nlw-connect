import { inArray } from 'drizzle-orm'
import { db } from '../drizzle/client'
import { subscriptions } from '../drizzle/schema/subscriptions'
import { redis } from '../redis/client'

export async function getRanking() {
  const ranking = await redis.zrevrange('referral:ranking', 0, 2, 'WITHSCORES')
  const subscribersIdsAndScores: Record<string, number> = {}

  for (let i = 0; i < ranking.length; i += 2) {
    subscribersIdsAndScores[ranking[i]] = Number.parseInt(ranking[i + 1])
  }

  const subscribersFromRanking = await db
    .select()
    .from(subscriptions)
    .where(inArray(subscriptions.id, Object.keys(subscribersIdsAndScores)))

  const rankingWithScore = subscribersFromRanking
    .map(subscriber => {
      return {
        id: subscriber.id,
        name: subscriber.name,
        score: subscribersIdsAndScores[subscriber.id],
      }
    })
    .sort((a, b) => b.score - a.score)

  return {
    rankingWithScore,
  }
}
