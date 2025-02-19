import { BadgeCheck, Medal, MousePointerClick } from 'lucide-react'

export function Stats() {
  return (
    <div className="grid md:grid-cols-3 gap-3">
      <div className="relative px-4 py-7 bg-gray-700 border border-gray-600 rounded-xl flex flex-col items-center justify-center gap-1">
        <span className="font-heading text-2xl font-semibold text-gray-200 leading-none">
          1042
        </span>

        <span className="text-sm text-gray-300 leading-none text-center">
          Acessos ao link
        </span>

        <MousePointerClick className="absolute top-3 left-3 size-5 text-purple" />
      </div>

      <div className="relative px-4 py-7 bg-gray-700 border border-gray-600 rounded-xl flex flex-col items-center justify-center gap-1">
        <span className="font-heading text-2xl font-semibold text-gray-200 leading-none">
          1042
        </span>

        <span className="text-sm text-gray-300 leading-none text-center">
          Inscrições feitas
        </span>

        <BadgeCheck className="absolute top-3 left-3 size-5 text-purple" />
      </div>

      <div className="relative px-4 py-7 bg-gray-700 border border-gray-600 rounded-xl flex flex-col items-center justify-center gap-1">
        <span className="font-heading text-2xl font-semibold text-gray-200 leading-none">
          3&ordm;
        </span>

        <span className="text-sm text-gray-300 leading-none text-center">
          Posição no ranking
        </span>

        <Medal className="absolute top-3 left-3 size-5 text-purple" />
      </div>
    </div>
  )
}
