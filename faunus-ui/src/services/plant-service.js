import get from './http-client'

export function displayPlants() {
    return get('/plants');
}