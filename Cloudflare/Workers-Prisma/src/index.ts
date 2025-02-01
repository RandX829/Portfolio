import { PrismaClient } from "@prisma/client";
import { PrismaD1 } from "@prisma/adapter-d1"

export default {
	async fetch(request, env, ctx): Promise<Response> {
		const adapter = new PrismaD1(env.DB);
		const prisma = new PrismaClient({ adapter });

		const categories = await prisma.category.findMany();
		const result = JSON.stringify(categories);

		return new Response(result);
	},
} satisfies ExportedHandler<Env>;
