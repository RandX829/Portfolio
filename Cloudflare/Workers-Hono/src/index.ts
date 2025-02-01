import { Hono } from 'hono'

const app = new Hono<{ Bindings: CloudflareBindings }>()

app.get('/', (cxt) => {
  return cxt.text('Hello My First Workers + Hono App!')
})

export default app