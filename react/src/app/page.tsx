import { Button } from '@/components/button'
import { IconButton } from '@/components/icon-button'
import { InputField, InputIcon, InputRoot } from '@/components/input'
import { ArrowRight, Copy, Mail } from 'lucide-react'

export default function Home() {
  return (
    <main>
      <Button>
        teste
        <ArrowRight />
      </Button>

      <IconButton>
        <Copy />
      </IconButton>

      <InputRoot>
        <InputIcon>
          <Mail />
        </InputIcon>

        <InputField placeholder="Digite seu e-mail" />
      </InputRoot>

      <InputRoot error>
        <InputIcon>
          <Mail />
        </InputIcon>

        <InputField placeholder="Digite seu e-mail" />
      </InputRoot>
    </main>
  )
}
