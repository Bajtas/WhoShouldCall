using System;
using System.Linq;
using System.Security.Principal;
using WSCData;
using WSCData.Models;
using WSCData.Models.Entities;

namespace WSCCore.Services
{
    public class QueueCallerService : QueueService
    {
        public QueueCallerService()
            : base()
        {

        }

        public QueueCallerService(WSCDbContext ctx)
            : base(ctx)
        {

        }

        public void SetUserAsCallerOrNot(string userName, bool callerValue)
        {
            var dbUser = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            if (dbUser != null)
            {
                dbUser.IsCaller = callerValue;
            }
        }

        public void SetUserAsCaller(string userName)
        {
            SetUserAsCallerOrNot(userName, true);
        }

        public void SetUserAsNonCaller(string userName)
        {
            SetUserAsCallerOrNot(userName, false);
        }

        public void SetUserAsCallerAndUpdateLastCallDate(string userName)
        {
            SetUserAsCallerOrNot(userName, true);
            UpdateLastCallDate(userName, DateTime.Now);
        }

        public void SetUserAsNonCallerAndRestorePreviousLastCallDate(string userName)
        {
            SetUserAsCallerOrNot(userName, false);
            RestorePreviousLastCallDate(userName);
        }

        private void UpdateLastCallDate(string userName, DateTime now)
        {
            var dbUser = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            if (dbUser != null)
            {
                dbUser.LastCallBackup = dbUser.LastCall;
                dbUser.LastCall = now;
                dbUser.IsCallConfirmed = false;
            }
        }

        private void RestorePreviousLastCallDate(string userName)
        {
            var dbUser = _ctx.Users.Where(u => u.UserName == userName).FirstOrDefault();
            if (dbUser != null)
            {
                dbUser.LastCall = dbUser.LastCallBackup;
                if (dbUser.LastCall != null)
                    dbUser.IsCallConfirmed = true;
            }
        }

        public bool ThereIsStillNobodyToCall(Queue queue)
        {
            return queue.Users.All(u => u.IsCaller == false);
        }

        public string WhosTheCaller(Queue queue)
        {
            var user = queue.Users.Where(u => u.IsCaller).FirstOrDefault();
            return user.UserName;
        }

        public void RemoveCurrentCaller(int? queueId)
        {
            var queue = _ctx.Queues.Find(queueId);
            if (queue != null)
            {
                var currentCaller = queue.Users.Where(u => u.IsCaller).FirstOrDefault();
                if (currentCaller != null)
                {
                    SetUserAsNonCallerAndRestorePreviousLastCallDate(currentCaller.UserName);
                }
            }
        }
    }
}