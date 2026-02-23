import React from 'react';
import { clsx } from 'clsx';
import { twMerge } from 'tailwind-merge';

const cn = (...inputs) => twMerge(clsx(inputs));

const IconCircle = ({ icon: Icon, color = 'blue', size = 18, className }) => {
    const colorClasses = {
        blue: 'bg-apple-blue',
        red: 'bg-apple-red',
        orange: 'bg-apple-orange',
        yellow: 'bg-apple-yellow',
        green: 'bg-apple-green',
        mint: 'bg-apple-mint',
        teal: 'bg-apple-teal',
        cyan: 'bg-apple-cyan',
        indigo: 'bg-apple-indigo',
        purple: 'bg-apple-purple',
        pink: 'bg-apple-pink',
        gray: 'bg-apple-gray',
        'gray-2': 'bg-apple-gray-2',
    };

    return (
        <div className={cn(
            "p-1.5 rounded-full text-white flex items-center justify-center",
            colorClasses[color] || 'bg-apple-blue',
            className
        )}>
            <Icon size={size} />
        </div>
    );
};

export default IconCircle;
