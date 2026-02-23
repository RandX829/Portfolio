/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                apple: {
                    blue: "#007AFF",
                    red: "#FF3B30",
                    orange: "#FF9500",
                    yellow: "#FFCC00",
                    green: "#34C759",
                    mint: "#00C7BE",
                    teal: "#30B0C7",
                    cyan: "#32ADE6",
                    indigo: "#5856D6",
                    purple: "#AF52DE",
                    pink: "#FF2D55",
                    brown: "#A2845E",
                    gray: "#8E8E93",
                    "gray-2": "#AEAEB2",
                    "gray-3": "#C7C7CC",
                    "gray-4": "#D1D1D6",
                    "gray-5": "#E5E5EA",
                    "gray-6": "#F2F2F7",
                },
            },
            fontFamily: {
                sans: [
                    "-apple-system",
                    "BlinkMacSystemFont",
                    '"Segoe UI"',
                    "Roboto",
                    "Helvetica",
                    "Arial",
                    "sans-serif",
                    '"Apple Color Emoji"',
                    '"Segoe UI Emoji"',
                    '"Segoe UI Symbol"',
                ],
            },
            boxShadow: {
                'ios': '0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)',
            }
        },
    },
    plugins: [],
}
