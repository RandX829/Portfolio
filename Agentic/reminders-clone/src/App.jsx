import React from 'react';
import Sidebar from './components/Sidebar';
import ReminderList from './components/ReminderList';

function App() {
    return (
        <div className="flex h-screen w-screen overflow-hidden font-sans">
            <Sidebar />
            <main className="flex-1 min-w-0">
                <ReminderList />
            </main>
        </div>
    );
}

export default App;
