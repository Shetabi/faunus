import get from './http-client'

export function sayHello() {
    return get('/hello');
}