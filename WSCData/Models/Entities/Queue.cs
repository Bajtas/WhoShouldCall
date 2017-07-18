using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WSCData.Models.Entities
{
    public class Queue
    {
        public Queue()
        {
            Orders = new List<Order>();
            Users = new List<User>();
            Moderators = new List<User>();
        }
        public int Id { get; set; }
        [Display(Name = "Queue name:")]
        public string Name { get; set; }
        public virtual List<Order> Orders { get; set; }
        [Display(Name = "Users in queue:")]
        public virtual List<User> Users { get; set; }
        public virtual List<User> Moderators { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null || GetType() != obj.GetType())
            {
                return false;
            }
            Queue q = obj as Queue;

            return Name.Equals(q?.Name);
        }
    }
}
