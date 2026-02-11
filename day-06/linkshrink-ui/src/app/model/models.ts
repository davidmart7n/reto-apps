export interface Link{
    id:number,
    originalUrl:string,
    shortUrl:string,
    shortCode:string,
    clicks:number,
}

export interface CreateLinkRequest{
    url:string
}