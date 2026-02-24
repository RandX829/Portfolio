import localforage from 'localforage';
import type { List, Reminder } from '@/types';

localforage.config({
  name: 'RemindersClone',
  storeName: 'reminders_data',
});

const STORAGE_KEY = 'reminders-data';

export interface StorageData {
  lists: List[];
  reminders: Reminder[];
}

export async function loadData(): Promise<StorageData> {
  try {
    const data = await localforage.getItem<StorageData>(STORAGE_KEY);
    if (data) {
      return data;
    }
  } catch (error) {
    console.error('Error loading data:', error);
  }
  return { lists: [], reminders: [] };
}

export async function saveData(data: StorageData): Promise<void> {
  try {
    await localforage.setItem(STORAGE_KEY, data);
  } catch (error) {
    console.error('Error saving data:', error);
  }
}
