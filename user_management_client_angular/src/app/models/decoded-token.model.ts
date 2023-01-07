/* DecodedToken is a class that has a property called user that is an object that has a property called
userRoles that is an array of objects that has a property called authority that is a string. */
export class DecodedToken {
  exp: string;
  iat: string;
  sub: string;
  user: {
    id: number;
    username: string;
    userRoles: [
      {
        authority: string;
      }
    ];
    name: string;
  };
}
