interface ButtonProps {
  text?: string;
}

export function Button({ text }: ButtonProps) {
  return <button className="">{text}</button>;
}
