/**
 * Welcome to Cloudflare Workers! This is your first worker.
 *
 * - Run `npm run dev` in your terminal to start a development server
 * - Open a browser tab at http://localhost:8787/ to see your worker in action
 * - Run `npm run deploy` to publish your worker
 *
 * Bind resources to your worker in `wrangler.json`. After adding bindings, a type definition for the
 * `Env` object can be regenerated with `npm run cf-typegen`.
 *
 * Learn more at https://developers.cloudflare.com/workers/
 */

import { Hono } from "hono";
import { logger } from "hono/logger"
import { prettyJSON } from "hono/pretty-json";

type Bindings = {
	DB: D1Database;
}

const app = new Hono<{ Bindings: Bindings }>();

app.use("*", prettyJSON(), logger())

app.get("/api/transactions", async (ctx) => {
	try {
		const query = `
		SELECT * FROM transactions
		`;
	
		const result = await ctx.env.DB.prepare(query)
		.run()

		return ctx.json(result);

	} catch (err) {
		return ctx.json({ error: `Failed to fetch transactions!` }, 500);
	}
});

app.get("/api/transactions/:id", async (ctx) => {
	try {
		const { id } = ctx.req.param()

		const query = `
		SELECT * FROM transactions WHERE id = ?
		`;
	
		const result = await ctx.env.DB.prepare(query)
		.bind(id)
		.run()

		return ctx.json(result);

	} catch (err) {
		return ctx.json({ error: `Failed to fetch transaction!` }, 500);
	}
});

app.post("/api/transactions/create", async (ctx) => {
	try {
		const { amount } = await ctx.req.json();
		
		const query = `
		INSERT INTO transactions (amount)
		VALUES (?)
		`;
		
		const result = await ctx.env.DB.prepare(query)
		.bind(amount)
		.run();

		return ctx.json(result);

	} catch (err) {
		return ctx.json({ error: `Failed to create transaction!` }, 500);
	}
});

app.delete("/api/transactions/delete/:id", async (ctx) => {
	try {
		const id = ctx.req.param("id");

		const entry = await ctx.env.DB.prepare("SELECT * FROM transactions WHERE id = ?")
		.bind(id)
		.run();

		if(entry.results.length == 0) {
			return ctx.json({ error : "Not Found" }, 404);
		}

		const query = `
		DELETE FROM transactions WHERE id = ?
		`;
	
		const result = await ctx.env.DB.prepare(query)
		.bind(id)
		.run()

		return ctx.json(result);

	} catch (err) {
		return ctx.json({ error: `Failed to delete transaction!` }, 500);
	}
});
  
export default app
