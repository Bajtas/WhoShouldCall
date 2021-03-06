﻿using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Collections.Generic;

namespace WSCData.Models.Entities
{
    public class User : IdentityUser
    {
        public User()
        {
            Orders = new List<Order>();
        }

        public User(String userName) : this()
        {
            base.UserName = userName;
        }

        public bool IsCaller { get; set; }
        public bool IsCallConfirmed { get; set; }
        public Queue Queue { get; set; }
        public DateTime? LastCall { get; set; }
        public DateTime? LastCallBackup { get; set; }
        public virtual List<Order> Orders { get; set; }

        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<User> manager)
        {
            var userIdentity = await manager.CreateIdentityAsync(this, DefaultAuthenticationTypes.ApplicationCookie);
            return userIdentity;
        }
    }
}